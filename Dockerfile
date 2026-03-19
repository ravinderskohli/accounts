FROM python:3.12-slim
WORKDIR /app
COPY . .
RUN pip install fastmcp uvicorn fastapi==0.115.0
EXPOSE 80
CMD ["python", "main.py"]
