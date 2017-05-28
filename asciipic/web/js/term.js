var TOKEN = null,
    RETRY_COUNT = 15,
    RETRY_DELAY = 60;

function setSize()
{
	let height = $("#term-row").height() - $("footer").height() - 100;
	$("#term").css("height", height);
}


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
        if(response["meta"]["status"]){
            if(response["content"]["token"]){
                TOKEN = response["content"]["token"];
                localStorage.setItem("TOKEN", TOKEN)
                terminal.echo("Token :" + TOKEN);
            }else{
                terminal.echo(response["meta"]["verbose"]);
            }
        }else{
            terminal.echo(response["meta"]["verbose"]);
        }
    });
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function wait_for_job(job_id, path, terminal){
    // for for the jobs id
    //
    // @param job_id  
    // If of the job to wait
    //
    // @param path
    // The API endpoint to query
    //
    // @param terminatl
    // Reference to the terminal
    var fail = false,
        done = false;
    for(var i = 0; i<= RETRY_COUNT; i++){

        if(fail)
        {
            terminal.echo("Can't reach the API.");
            return null;
        }

        if(done)
        {
            console.log("Done waiting.")
            return null;
        }

        sleep(RETRY_DELAY);

        request = $.ajax({
            async: false,
            url: path + "/" + job_id,
            method: "GET",
            headers: {
                "Authorization": TOKEN
            },
        });

        request.fail(function( jqXHR, textStatus ) {
            terminal.echo("Error: " + textStatus );
            fail = true;
        });

        request.done(function(response){
            if(response["meta"]["status"]){
                // need to wait for the job to finish
                var echo_id = response["meta"]["job_id"];
                if(response["meta"]["job_status"] === "finished"){
                    terminal.echo(response["content"]);
                    done = true;
                }else
                {
                    console.log("Retry " + i);
                    console.log(response);
                }
            }else{
                terminal.echo(response["meta"]["verbose"]);
                fail = true;
            }
        });

    }
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
            // need to wait for the job to finish
            var job_id = response["meta"]["job_id"];
            terminal.echo("Id :"+job_id);
            wait_for_job(job_id, "/api/echo", terminal)
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
