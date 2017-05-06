$(function() {
    $("#goButton").click(function(e){
        //for json
        ipInput = "";
        tagInput = "";
        postDateInput = "";
        pictureSizeHeightInput = "";
        pictureSizeWidthInput = "";
        userAgentInput = "";
        filtersTypeInput = "";
        causesInput = "";
        timeInput = "";
        typeOfTimeInput = "";

        mainCommandInput =$('#mainCommandInput option:selected').val();

        switch(mainCommandInput){
            case "LOGIN":
                loginWithSmthInput = $('#loginWithSmthInput option:selected').val();
                if(loginWithSmthInput == "NONE"){
                    ipInput = "";
                }
                else{
                    ipInput =  $('#ip').val();
                }
                userAgentInput = $('#userAgentInput option:selected').val();
                if(userAgentInput == "NONE"){
                    userAgentInput = "";
                }   
                break;
            case "LOGOUT":
                causesInput = $('#causesInput option:selected').val();
                if(causesInput == "NATURAL_AND_ARTIFICIAL"){
                    causesInput = "";
                }
                break;
            case "REGISTER":
                loginWithSmthInput = $('#loginWithSmthInput option:selected').val();
                if(loginWithSmthInput == "NONE"){
                    ipInput = "";
                }
                else{
                    ipInput =  $('#ip').val();
                }
                break;
            case "SEARCH":
                tagInput = $('#tag').val();
                postDateInput = $('#postDate').val();
                pictureSizeHeightInput = $('#pictureSizeHeight').val();
                pictureSizeWidthInput = $('#pictureSizeWidth').val();
                break;
            case "CRAWL":
                tagInput = $('#tag').val();
                postDateInput = $('#postDate').val();
                pictureSizeHeightInput = $('#pictureSizeHeight').val();
                pictureSizeWidthInput = $('#pictureSizeWidth').val();
                break;
            case "FILTERS":
                filtersTypeInput = $('#filtersTypeInput option:selected').val();
                if(filtersTypeInput == "NONE"){
                    filtersTypeInput = "";
                }
                break;
            default: 
                $('#statisticResults').text("Something is wrong!");
                break;
        }

        typeOfTimeInput = $('#typeOfTimeInput option:selected').val();

        if(typeOfTimeInput == "FROM_THE_BEGINING"){
            timeInput = "";
            typeOfTimeInput = "";
        }
        else{
            timeInput = $('#time').val();
        }

        informationsJSON = {
                    mainCommand: mainCommandInput,
                    ip: ipInput,
                    tag: tagInput,
                    postDate: postDateInput,
                    pictureSizeHeight: pictureSizeHeightInput,
                    pictureSizeWidth: pictureSizeWidthInput,
                    userAgent: userAgentInput,
                    filtersType: filtersTypeInput,
                    causes: causesInput,
                    time:timeInput,
                    typeOfTime: typeOfTimeInput
                };

        textDeAfisat = informationsJSON['mainCommand'] 
                        + "<br>" + informationsJSON['ip'] 
                        + "<br>" + informationsJSON['tag'] 
                        + "<br>" + informationsJSON['postDate'] 
                        + "<br>" + informationsJSON['pictureSizeHeight'] 
                        + "<br>" + informationsJSON['pictureSizeWidth'] 
                        + "<br>" + informationsJSON['userAgent']
                        + "<br>" + informationsJSON['filtersType']
                        + "<br>" + informationsJSON['causes']
                        + "<br>" + informationsJSON['time']
                        + "<br>" + informationsJSON['typeOfTime'];
        $('#statisticResults').html(textDeAfisat);

        /* NOTE(AlexandraFolvaiter): fix it!*/
        //send request to server
         e.preventDefault();
        $.ajax({
        url: "http://localhost:8084/v1/statistic",
        headers: {
            "content-type": "application/json"
          },
        type: "POST",
        data: informationsJSON,
        dataType: "json",
        "processData": false,
        success: function(response) {
          alert(response);
        },
        error: function(response) {
          console.log(response);
        }
      });

    });

});
