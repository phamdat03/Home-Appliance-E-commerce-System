
<style>
  #chatbot-btn {
      position: fixed;
      bottom: 20px;
      right: 20px;
      width: 60px;
      height: 60px;
      background: #B01032;
      color: white;
      border-radius: 50%;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      font-size: 28px;
      z-index: 9999;
      box-shadow: 0 4px 10px rgba(0,0,0,0.3);
  }

  #chatbot-box {
      position: fixed;
      bottom: 90px;
      right: 20px;
      width: 350px;
      height: 450px;
      background: white;
      border-radius: 12px;
      box-shadow: 0 4px 20px rgba(0,0,0,0.2);
      display: none;
      flex-direction: column;
      overflow: hidden;
      z-index: 9999;
  }

  #chat-header {
      background: #ff3d3d;
      color: white;
      padding: 12px;
      text-align: center;
      font-weight: bold;
  }

  #chat-body {
      flex: 1;
      padding: 10px;
      overflow-y: auto;
      font-size: 14px;
  }

  .msg {
      margin-bottom: 10px;
      padding: 8px;
      border-radius: 8px;
      max-width: 85%;
  }

  .user-msg { background: #e3f2fd; align-self: flex-end; }
  .bot-msg  { background: #f1f1f1; align-self: flex-start; }

  #chat-input-box {
      display: flex;
      border-top: 1px solid #ddd;
  }

  #chat-input {
      flex: 1;
      padding: 10px;
      border: none;
      font-size: 14px;
  }

  #send-btn {
      width: 70px;
      background: #ff3d3d;
      color: white;
      border: none;
      cursor: pointer;
  }
</style>


<div id="chatbot-btn">?</div>

<div id="chatbot-box">
    <div id="chat-header">Chatbot AI</div>
    <div id="chat-body"></div>
    <div id="chat-input-box">
        <input id="chat-input" type="text" placeholder="Enter text">
        <button id="send-btn">Send</button>
    </div>
</div>

 
<script>
document.getElementById("chatbot-btn").onclick = () => {
    let box = document.getElementById("chatbot-box");
    box.style.display = box.style.display === "flex" ? "none" : "flex";
};

// g?i tin nh?n
async function sendMessage() {
    const input = document.getElementById("chat-input");
    const body = document.getElementById("chat-body");
    const text = input.value.trim();
    if (!text) return;

    // show user message
    body.innerHTML += `<div class="msg user-msg">${text}</div>`;
    body.scrollTop = body.scrollHeight;
    input.value = "";

    // call Gemini API
    const res = await fetch("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyDvSPoBy0SNFdW-e4Yy4Z4-srqGdrOcJYk", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            contents: [{ parts: [{ text }] }]
        })
    });

    const data = await res.json();
    const botReply = data?.candidates?.[0]?.content?.parts?.[0]?.text || "L?i khi g?i AI";

    // show bot message
    body.innerHTML += `<div class="msg bot-msg">${botReply}</div>`;
    body.scrollTop = body.scrollHeight;
}

document.getElementById("send-btn").onclick = sendMessage;
document.getElementById("chat-input").addEventListener("keypress", function(e){
    if (e.key === "Enter") sendMessage();
});
</script>
