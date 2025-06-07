import os
from openai import OpenAI
from dotenv import load_dotenv

load_dotenv()


client = OpenAI(
    api_key=os.getenv("DEEPSEEK_API_KEY"), 
    base_url="https://api.deepseek.com"
)


def getSysPrompt():
    with open("prompt.txt", 'r') as archivo:
        return archivo.read()


my_prompt = """
Tema: código, Estilo: caliente, Cantidad: 25
"""


#def get_ds_response(user_prompt):

messages = [{"role": "system", "content": getSysPrompt()},
            {"role": "user", "content": my_prompt}]

response = client.chat.completions.create(
    messages=messages,
    model="deepseek-chat",
    temperature=2,
    stream=False,
    response_format={'type': 'json_object'}
)

print(response.choices[0].message.content)

