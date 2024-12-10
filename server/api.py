from flask import Flask, redirect, url_for, request
from flask import jsonify
import csv

app = Flask(__name__)

# Load from CSV
obstacles = [] # or maybe {} or [{}]
with open('obstacles.csv', 'r') as file:
    csvFile = csv.DictReader(file)
    for line in csvFile:
        obstacles.append(line)

@app.route('/obstacle', methods=['POST'])
def add_obstacle():
    # add to the obstacles
    obstacle = request.json
    obstacles.append(obstacle)
    # save to csv
    with open('obstacles.csv', 'a', newline='') as csvfile:
        fieldnames = ['x', 'y','id']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writerow(obstacle)

    return 'Success'

@app.route('/obstacle', methods=['DELETE'])
def del_obstacle():
    # del from obstacles (perhaps use an id)
    id = request.json
    for obstacle in obstacles:
        if id in obstacle.values():
            obstacles.remove(obstacle)

    #del from csv (rewrite without deleted value)
    with open('obstacles.csv', 'w', newline='') as csvfile:
        fieldnames = ['x', 'y','id']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(obstacles)
            

    return 'Success'

@app.route('/obstacles', methods=['GET'])
def get_obstacles():
    return jsonify(obstacles)




if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000)