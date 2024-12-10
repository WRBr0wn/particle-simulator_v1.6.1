# Base Image: Use a base image with Python and Java pre-installed
FROM python:3.10-slim AS base

# Set environment variables
ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1
ENV DISPLAY=host.docker.internal:0

# Install dependencies for Python, Java, and Processing
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-17-jdk \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME for Java
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Install Python dependencies
RUN pip install --upgrade pip

# Copy Python and Processing application files
WORKDIR /app
COPY server /app/server
COPY main /app/main

# Install Python dependencies from requirements.txt
WORKDIR /app/server
RUN pip install -r requirements.txt

# Expose Flask's default port
EXPOSE 5000

# Install Processing
WORKDIR /opt
RUN wget https://github.com/processing/processing4/releases/download/processing-1293-4.3/processing-4.3-linux-x64.tgz \
    && tar -xvzf processing-4.3-linux-x64.tgz \
    && rm processing-4.3-linux-x64.tgz
ENV PATH="/opt/processing-4.3:$PATH"

# Command to start Flask server & Processing
COPY start.sh /start.sh
RUN chmod +x /start.sh

CMD ["/start.sh"]
