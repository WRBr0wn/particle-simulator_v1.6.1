# Lightweight base image with Python and Java pre-installed
FROM python:3.10-slim AS base

# Set environment variables
ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1
ENV DISPLAY=host.docker.internal:0

# Install dependencies for Java and Processing
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-17-jdk \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Install Processing
WORKDIR /opt
RUN wget https://github.com/processing/processing4/releases/download/processing-1293-4.3/processing-4.3-linux-x64.tgz \
    && tar -xvzf processing-4.3-linux-x64.tgz \
    && rm processing-4.3-linux-x64.tgz
ENV PATH="/opt/processing-4.3:$PATH"

# Copy Python and Processing application files
WORKDIR /app
COPY server /app/server
COPY main /app/main

# Install Python dependencies from requirements.txt
RUN pip install -r /app/server/requirements.txt

# Copy the start.sh script to the root directory and make executable
COPY start.sh /start.sh
RUN chmod +x /start.sh

# Command to start Flask server & Processing
CMD ["/start.sh"]