## Spring security in action

1. `curl -I http://localhost:8080/ -H "user: user" -H "pass: 1234" `
   ![image1](./pictures/1.jpg)


2. `curl -I http://localhost:8080/ -H "user: user" -H "code: 1900" `
    ![image2](./pictures/2.jpg)


3. `curl http://localhost:8080/ -H "user: user" -H "Token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoidXNlciJ9.pCzy64ToBNcYug4_Mx7tb57c_2qTLVI0R7uqv2Rm0fc"`
    ![image3](./pictures/3.png)