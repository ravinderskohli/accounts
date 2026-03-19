FROM python:3.12-slim

WORKDIR /app

# Copy files
COPY . .

# Install deps (no cache issues)
RUN pip install --no-cache-dir fastmcp==3.0.2

# Cloud Run PORT
EXPOSE $PORT

CMD ["python", "main.py"]
