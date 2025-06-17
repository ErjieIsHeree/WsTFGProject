import os
import json
from openai import OpenAI
from dotenv import load_dotenv


load_dotenv()


client = OpenAI(
    api_key=os.getenv("DEEPSEEK_API_KEY"), 
    base_url="https://api.deepseek.com"
)


def getSysPrompt():
    with open("IA\\prompt.txt", 'r') as archivo:
        return archivo.read()


def get_ds_response(user_prompt):

    messages = [{"role": "system", "content": getSysPrompt()},
                {"role": "user", "content": json.dumps(user_prompt)}]

    response = client.chat.completions.create(
        messages=messages,
        model="deepseek-chat",
        temperature=2,
        stream=False,
        response_format={'type': 'json_object'}
    )

    print(response.choices[0].message.content)
    return response.choices[0].message.content

