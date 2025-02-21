# Android-Python
这是一个是使用python写的WebSocket链接使用
实现原理客户端连接服务器，服务器获取已经链接的客户端，并且Curl 
启动服务端
```
uvicorn main:app --reload --host 0.0.0.0 --port 8000
```
或者
```
python3 main.py
```
请求地址：
```
curl http://localhost:8000/get_input/android123
```
返回示例
```
{
  "android_id": "android123",
  "input_content": "Hello World!"
}
```
