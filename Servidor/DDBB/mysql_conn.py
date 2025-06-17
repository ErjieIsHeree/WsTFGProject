import pymysql

def conectar():
    return pymysql.connect(
        host='localhost',
        user='root',
        passwd='root',
        db='yo_nunca'
    )


def insertar_usuario(nick, pwd):
    conn = conectar()
    try:
        with conn.cursor() as cursor:
            sql = "INSERT INTO users (nickname, password) VALUES (%s, %s)"
            cursor.execute(sql, (nick, pwd))
            conn.commit()
            sql = "SELECT userId FROM users WHERE nickname = %s AND password = %s"
            cursor.execute(sql, (nick, pwd))
            result = cursor.fetchone()
            if result:
                return result[0]  # Devuelve solo el userId (int)
    except Exception as e:
        print(e)
    finally:
        conn.close()
    return 0


def actualizar_usuario(id, nuevoNick, nuevoPwd):
    conn = conectar()
    try:
        with conn.cursor() as cursor:
            sql = "UPDATE users SET nickname = %s, password = %s WHERE userId = %s"
            cursor.execute(sql, (nuevoNick, nuevoPwd, id))
        conn.commit()
    except Exception as e:
        print(e)
        return False
    finally:
        conn.close()
    return True


def existe_usuario(nick, pwd):
    conn = conectar()
    try:
        with conn.cursor() as cursor:
            sql = "SELECT userId FROM users WHERE nickname = %s AND password = %s"
            cursor.execute(sql, (nick, pwd))
            result = cursor.fetchone()
            if result:
                return result[0]  # Devuelve solo el userId (int)
            else:
                return 0
    except Exception as e:
        print(e)
    finally:
        conn.close()