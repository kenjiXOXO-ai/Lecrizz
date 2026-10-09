const express=require('express');const http=require('http');const {Server}=require('socket.io');const path=require('path');
const app=express(),server=http.createServer(app),io=new Server(server,{cors:{origin:'*'}});app.use(express.json());app.use(express.static(path.join(__dirname,'../dashboard/public')));
const users=new Map();
app.post('/api/heartbeat',(req,res)=>{const u=req.body||{};if(!u.userId)return res.status(400).json({error:'userId required'});users.set(u.userId,{userId:u.userId,name:u.name||'User',plan:u.plan||'Free',lastSeen:Date.now(),device:u.device||'Android'});io.emit('users',Array.from(users.values()));res.json({ok:true});});
app.get('/api/users',(req,res)=>res.json(Array.from(users.values())));
setInterval(()=>{const now=Date.now();for(const [id,u] of users)if(now-u.lastSeen>120000)users.delete(id);io.emit('users',Array.from(users.values()));},30000);
server.listen(process.env.PORT||3000,()=>console.log('LecRizz dashboard running on http://localhost:3000'));
