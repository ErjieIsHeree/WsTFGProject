from flask import Flask, jsonify, request

with open('datos.txt', 'r') as archivo:
    contenido = archivo.read()

print(contenido)