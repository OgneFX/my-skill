from flask import Flask, request, jsonify
import requests
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

# Укажите ваш токен Telegram-бота и ID чата
TELEGRAM_BOT_TOKEN = ''
CHAT_ID = ''

# Функция для отправки сообщения в Telegram
def send_to_telegram(name, phone):
    message = f"📨 Новое сообщение из формы:\n\n👤 Имя: {name}\n📞 Телефон: {phone}"
    url = f"https://api.telegram.org/bot{TELEGRAM_BOT_TOKEN}/sendMessage"
    payload = {
        'chat_id': CHAT_ID,
        'text': message,
        'parse_mode': 'HTML'  # Позволяет использовать HTML-форматирование
    }
    response = requests.post(url, json=payload)
    return response.json()

# Обработчик POST-запроса
@app.route('/send_message', methods=['POST'])
def send_message():
    try:
        # Получаем данные из тела запроса
        data = request.json

        name = data.get('name')
        phone = data.get('phone')

        # Проверка данных
        if not name or not phone:
            return jsonify({'status': 'error', 'message': 'Имя и телефон обязательны'}), 400

        # Отправка сообщения в Telegram
        result = send_to_telegram(name, phone)

        # Проверяем успешность отправки
        if result.get('ok'):

            return jsonify({'status': 'success', 'message': 'Сообщение отправлено!'})
        else:
            
            return jsonify({'status': 'error', 'message': 'Ошибка отправки в Telegram'}), 500
    except Exception as e:
        return jsonify({'status': 'error', 'message': str(e)}), 500

# Запуск приложения
if __name__ == '__main__':
    app.run(debug=True)