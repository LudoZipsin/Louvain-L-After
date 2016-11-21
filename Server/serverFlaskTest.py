from flask import request, Flask

app = Flask(__name__)

@app.route('/', methods=['POST'])
def log():
	print('\033[92m'+request.get_json().__str__()+'\033[0m')
	return "bonjour"

@app.route('/test')
def test():
	print("tested")
	return "tested"

if __name__ == "__main__":
	app.run(
		host="0.0.0.0",
		port=int("4242")
	)