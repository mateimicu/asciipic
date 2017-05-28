function setSize()
{
	let height = $("#term-row").height() - $("footer").height() - 100;
	$("#term").css("height", height);
}
var TOKEN = null;

function send_login(command, terminal){
    // Send the login credentials
    var params = command.split(" ");

    request = $.ajax({
        async: false,
        url: "/api/user/token",
        method: "POST",
        data: {
            "username": params[1],
            "password": params[2]
        }
    });

    request.fail(function( jqXHR, textStatus ) {
        terminal.echo("Error: " + textStatus );
    });

    request.done(function(response){
        if(response["content"]["token"]){
            TOKEN = response["content"]["token"];
            localStorage.setItem("TOKEN", TOKEN)
            terminal.echo("Token :" + TOKEN);
        }else{
            terminal.echo(response["meta"]["verbose"]);
        }
    });
}

function send_echo(command, terminal){
    // Send the login credentials
    request = $.ajax({
        async: false,
        url: "/api/echo",
        method: "POST",
        data: {
            "data": command
        },
        headers: {
            "Authorization": TOKEN
        },
    });

    request.fail(function( jqXHR, textStatus ) {
        terminal.echo("Error: " + textStatus );
    });

    request.done(function(response){
        if(response["meta"]["status"]){
            terminal.echo(response["content"]);
        }else{
            terminal.echo(response["meta"]["verbose"]);
        }
    });
}


(function() {
    'use strict';
    jQuery(function($, undefined) {
        $('#term').terminal(function(command) {
            // Preserve theterminal 
            let terminal = this;

            if(command.startsWith("login")){
                send_login(command, terminal);
            }else if(command.startsWith("echo")){
                send_echo(command, terminal) 
            }else{
                // aici parsam restul comenzilor
            } // end for login
        }, {
            greetings: 'AsciiPic Web CLI',
            prompt: 'asciipic ~$ ',
            resize: true,
            history: true,
        });
    });

	// Resize
	setSize();
	$(window).resize(setSize);
})();
