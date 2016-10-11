let vscode = require('vscode');

function activate(context) {
    console.log('Extension "sockets-and-plugs-vscode-demo" is now active!');

    let disposable = vscode.commands.registerCommand('extension.sayHello', () => {
        vscode.window.showInformationMessage('Hello World!');
    });
    context.subscriptions.push(disposable);
}
exports.activate = activate;

function deactivate() {}
exports.deactivate = deactivate;
