from flask import Flask, jsonify, request
from IA.deepseek_conn import get_ds_response
from DDBB import mysql_conn


app = Flask(__name__)


#Pide al IA respuestas
#Request: {"Topic": "user_input", "Style": "user_input", "Quantity": "user_input"}
#Return: {"f": ["phrase1", "phrase2", ..., "phraseX"]}
@app.route('/deepseek', methods=['POST'])
def get_ai_response():
    data = request.get_json()
    response = get_ds_response(data)
    return jsonify(response), 200


#Crea usuario
#Request: {"nick": <valor>, "pwd": <valor>}
#Return: An id >= 1 if succed and 0 if not
@app.route('/users', methods=['POST'])
def create_user():
    data = request.get_json()
    succeed = mysql_conn.insertar_usuario(data['nick'], data['pwd'])
    return jsonify({"Succeed": succeed}), 200


#Existe tupla igual
#Request: "id": <valor>, "nick": <valor>, "pwd": <valor>}
#Return: True if does and False if does not
@app.route('/users', methods=['GET'])
def get_exists():
    nick = request.args.get('nick')
    pwd = request.args.get('pwd')
    succeed = mysql_conn.existe_usuario(nick, pwd)
    return jsonify({"Succeed": succeed}), 200


#Cambia valores de usuario
#Request: {"id": <valor>, "newNick": <valor>, "newPwd": <valor>}
#Return: An id >= 1 if succed and 0 if not
@app.route('/users', methods=['PATCH'])
def modify_user():
    data = request.get_json()
    succeed = mysql_conn.actualizar_usuario(data['id'], data['newNick'], data['newPwd'])
    return jsonify({"Succeed": succeed}), 200


if __name__ == '__main__':
    app.run(debug=True, threaded=True)
