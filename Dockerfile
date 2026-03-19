FROM python:3.12-slim
WORKDIR /app
COPY . .
RUN pip install fastmcp==3.0.2
EXPOSE $PORT
CMD ["python", "main.py"]
