function setSize()
{
	let height =$("#term-row").height() - $("footer").height() - 100;
	$("#term").css("height", height);
}
(function() {
    'use strict';
    jQuery(function($, undefined) {
        $('#term').terminal(function(command) {
            this.echo("Rez :"+ String(command));
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
