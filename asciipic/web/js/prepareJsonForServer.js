function sendInformations(){
    //for json
    var mainCommandInput;
    var ipInput = "";
    var tagInput = "";
    var postDateInput = "";
    var pictureSizeHeightInput = "";
    var pictureSizeWidthInput = "";
    var userAgentInput = "";
    var filtersTypeInput = "";
    var causesInput = "";
    var timeInput = "";
    var typeOfTimeInput = "";

    mainCommandInput = (document.getElementById("mainCommandInput").getElementsByTagName("option")[document.getElementById("mainCommandInput").selectedIndex].value);

    switch(mainCommandInput){
        case "LOGIN":
            loginWithSmthInput = (document.getElementById("loginWithSmthInput").getElementsByTagName("option")[document.getElementById("loginWithSmthInput").selectedIndex].value);
            if(loginWithSmthInput == "NONE"){
                ipInput = "";
            }
            else{
                ipInput =  document.getElementById("ip").value;
            }
            userAgentInput = (document.getElementById("userAgentInput").getElementsByTagName("option")[document.getElementById("userAgentInput").selectedIndex].value);
            if(userAgentInput == "NONE"){
                userAgentInput = "";
            }   
            break;
        case "LOGOUT":
            document.getElementById('statisticResults').innerHTML = "logout";
            causesInput = (document.getElementById("causesInput").getElementsByTagName("option")[document.getElementById("causesInput").selectedIndex].value);
            if(causesInput == "NATURAL_AND_ARTIFICIAL"){
                causesInput = "";
            }
            break;
        case "REGISTER":
            loginWithSmthInput = (document.getElementById("loginWithSmthInput").getElementsByTagName("option")[document.getElementById("loginWithSmthInput").selectedIndex].value);
            if(loginWithSmthInput == "NONE"){
                ipInput = "";
            }
            else{
                ipInput =  document.getElementById("ip").value;
            }
            break;
        case "SEARCH":
            tagInput = document.getElementById("tag").value;
            postDateInput = document.getElementById("postDate").value;
            pictureSizeHeightInput = document.getElementById("pictureSizeHeight").value;
            pictureSizeWidthInput = document.getElementById("pictureSizeWidth").value;
            break;
        case "CRAWL":
            tagInput = document.getElementById("tag").value;
            postDateInput = document.getElementById("postDate").value;
            pictureSizeHeightInput = document.getElementById("pictureSizeHeight").value;
            pictureSizeWidthInput = document.getElementById("pictureSizeWidth").value;
            break;
        case "FILTERS":
            filtersTypeInput = (document.getElementById("filtersTypeInput").getElementsByTagName("option")[document.getElementById("filtersTypeInput").selectedIndex].value);
            if(filtersTypeInput == "NONE"){
                filtersTypeInput = "";
            }
            break;
        default: 
            document.getElementById('statisticResults').innerHTML = "Something is wrong! Try again!";
            break;
    }

    typeOfTimeInput = (document.getElementById("typeOfTimeInput").getElementsByTagName("option")[document.getElementById("typeOfTimeInput").selectedIndex].value);

    if(typeOfTimeInput == "FROM_THE_BEGINING"){
        timeInput = "";
    }
    else{
        timeInput = document.getElementById("time").value;
    }

    var informationsJSON = {
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
    //verif
    document.getElementById('statisticResults').innerHTML = informationsJSON['mainCommand'] 
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

    /* NOTE(AlexandraFolvaiter): fix it!*/
    // var xhr = new XMLHttpRequest();
    // xhr.open("POST","http://localhost:8084/v1/students");
    // xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    // var j = informationsJSON;
    // xhr.send(JSON.stringify(j));
}

