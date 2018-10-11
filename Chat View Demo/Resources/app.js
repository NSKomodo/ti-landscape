var ChatView = require('com.fanhero.chatview');
var chatView = ChatView.createChatView({
    width: Ti.UI.FILL,
    height: Ti.UI.FILL
}); 

var win = Ti.UI.createWindow({
    title: 'Chat View Demo',
    backgroundColor: '#fff'
});
win.add(chatView);
win.open();
