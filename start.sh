#!/bin/bash

# Change to Server Directory and Start Python API
cd /app/server
python api.py &

# Run the Processing sketch
processing-java --sketch=/app/main --run