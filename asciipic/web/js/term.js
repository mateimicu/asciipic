function setSize()
{
	let height =$("#term-row").height() - $("footer").height() - 100;
	$("#term").css("height", height);
}
(function() {
    'use strict';
    jQuery(function($, undefined) {
        $('#term').terminal(function(command) {
            let terminal = this;
            // Parse the actions
            var request = $.ajax({
                async: false,
                url: "/parse",
                method: "GET"
            });
            request.fail(function( jqXHR, textStatus ) {
                terminal.echo("Error: " + textStatus );
            });

            request.done(function(response){
                // HERE we have the actons, we can make all the requests
                // terminal.echo(String(response))
                // console.log(response)
                var old_response = {
                    "exitcode": undefined,
                    "data": undefined,
                };
                // for each(var action in response["actions"])
                response["actions"].forEach(function(action)
                {
                    console.log("Evaluate" + action)
                    switch(action["type"])
                    {
                        case 'pipe':
                            console.log("Pase pipe");
                            $.ajax({
                                async: false,
                                url: action["url"],
                                method: action["method"],
                                data: old_response
                            }).done(function(response){
                                console.log("pipe Response ");
                                console.log(response);
                                old_response = response;
                            }).fail(function( jqXHR, textStatus ) {
                                console.log("Fail ");
                                console.log(jqXHR);
                                console.log(textStatus);
                                terminal.echo("Error: " + textStatus );
                            });
                            break;

                        case 'and':
                            console.log("Pase and");
                            if(old_response["exitcode"] == false)
                            {
                                // break the chain
                                console.log("Pase and-break");
                                break;
                            }
                            $.ajax({
                                async: false,
                                url: action["url"],
                                method: action["method"],
                            }).done(function(response){
                                console.log("and-Response ");
                                console.log(response);
                                old_response = response;
                            }).fail(function( jqXHR, textStatus ) {
                                console.log("Fail ");
                                console.log(jqXHR);
                                console.log(textStatus);
                                terminal.echo("Error: " + textStatus );
                            });
                            break;

                        case 'or':
                            console.log("Pase or");
                            if(old_response["exitcode"] === true)
                            {
                                // break the chain
                                console.log("Pase or-break");
                                break;
                            }
                            $.ajax({
                                async: false,
                                url: action["url"],
                                method: action["method"],
                            }).done(function(response){
                                console.log("or-Response ");
                                console.log(response);
                                old_response = response;
                            }).fail(function( jqXHR, textStatus ) {
                                console.log("Fail ");
                                console.log(jqXHR);
                                console.log(textStatus);
                                terminal.echo("Error: " + textStatus );
                            });
                            break;

                        case 'end':
                            console.log("Pase end");
                            $.ajax({
                                async: false,
                                url: action["url"],
                                method: action["method"],
                            }).done(function(response){
                                console.log("end-Response ");
                                console.log(response);
                                old_response = response;

                                // end
                            }).fail(function( jqXHR, textStatus ) {
                                console.log("Fail ");
                                console.log(jqXHR);
                                console.log(textStatus);
                                terminal.echo("Error: " + textStatus );
                            });
                            break;

                        default:
                            console.log("Pase default");
                            $.ajax({
                                async: false,
                                url: action["url"],
                                method: action["method"],
                            }).done(function(response){
                                console.log("default-Response ");
                                console.log(response);
                                old_response = response;
                            }).fail(function( jqXHR, textStatus ) {
                                console.log("Fail ");
                                console.log(jqXHR);
                                console.log(textStatus);
                                terminal.echo("Error: " + textStatus );
                            });
                            break;
                    } // end switch
                    console.log("Step :");
                    console.log(action);
                    console.log("Response :");
                    console.log(old_response);
                }); // end for each
                terminal.echo(String(old_response["visual"]));
                console.log("Finish :")
                console.log(old_response)
                console.log("Finish visual:")
                console.log(old_response["visual"])
            });
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
