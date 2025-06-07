from flask import Flask, jsonify, request
from ai_api.api_ds import get_ds_response
from ai_api.api_oa import get_oa_response


app = Flask(__name__)

def get_response():
    return get_ds_response()

@app.route('/')
def home():
    return "¡Bienvenido a la API!" 

# Obtener todos los usuarios
@app.route('/usuarios', methods=['GET'])
def obtener_usuarios():
    return jsonify({"Response": get_response()})

# Obtener un usuario por ID
@app.route('/usuarios/<int:usuario_id>', methods=['GET'])
def obtener_usuario(usuario_id):
    usuario = next((u for u in usuarios if u["id"] == usuario_id), None)
    if usuario:
        return jsonify(usuario)
    return jsonify({"mensaje": "Usuario no encontrado"}), 404

# Agregar un nuevo usuario
@app.route('/usuarios', methods=['POST'])
def agregar_usuario():
    nuevo_usuario = request.get_json()
    usuarios.append(nuevo_usuario)
    return jsonify(nuevo_usuario), 201


if __name__ == '__main__':
    app.run(debug=True, threaded=True)