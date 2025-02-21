# Android-Python
# 实现原理及流程
1. 安卓端通过WebSocket连接到服务端，并在连接建立时发送自己的ID，服务端将ID和对应的WebSocket连接保存起来，比如在一个字典里。

2. 服务端提供一个HTTP接口，例如GET /get_input/{android_id}，当调用这个接口时，服务端查找对应的WebSocket连接，然后通过该连接发送请求给安卓端，要求返回输入框内容。

3. 安卓端在收到服务端的请求后，读取当前输入框的内容，通过WebSocket发送回服务端，服务端再将这个内容作为HTTP接口的响应返回给调用方。

# 启动服务端
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
