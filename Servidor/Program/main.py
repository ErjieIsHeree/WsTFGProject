from flask import Flask, jsonify, request
from api_ds import get_ds_response


#Cuál modelo usar
def get_ai_response():
    return get_ds_response()


app = Flask(__name__)


# Agregar un nuevo usuario
@app.route('/', methods=['POST'])
def agregar_usuario():
    data = request.get_json()
    print(data)
    response = get_ai_response(data)
    return jsonify(response), 201


if __name__ == '__main__':
    app.run(debug=True, threaded=True)