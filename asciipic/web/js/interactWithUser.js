function mainMenu(){
    var optionNumber = document.getElementById("mainCommandInput").selectedIndex;
    var resultOptionValue = (document.getElementById("mainCommandInput").getElementsByTagName("option")[optionNumber].value);

    if(resultOptionValue == "LOGIN"){
        //apelare functie de login after close all inputs 
        closeAll();
        loginOption();
    }else if(resultOptionValue == "LOGOUT"){
        //apelare functie logout
        closeAll();
        logoutOption();
    }else if(resultOptionValue == "REGISTER"){
        //apelaare functie register
        closeAll();
        registerOption();
    }else if(resultOptionValue == "SEARCH"){
        //apelare functie search
        closeAll();
        searchOption();
    }else if(resultOptionValue == "CRAWL"){
        //apelare functie CRAWL
        closeAll();
        crawlOption();
    }else if(resultOptionValue == "FILTERS"){
        //apelare functie Filters
        closeAll();
        filtersOption();
    }else {
        closeAll();
    }
}

function closeAll(){
    document.getElementById("loginWithSmthInput").style.display = "none";
    document.getElementById("ipInput").style.display = "none";
    document.getElementById("userAgentInput").style.display = "none";
    document.getElementById("timeInput").style.display = "none";
    document.getElementById("typeOfTimeInput").style.display = "none";
    document.getElementById("causesInput").style.display = "none";
    document.getElementById("tagInput").style.display = "none";
    document.getElementById("postDateInput").style.display = "none";
    document.getElementById("pictureSizeInput").style.display = "none";
    document.getElementById("filtersTypeInput").style.display = "none";
}

function loginOption(){
    document.getElementById('loginWithSmthInput').style.display = "inline";
    document.getElementById('timeInput').style.display = "inline";
    document.getElementById('typeOfTimeInput').style.display = "inline";
    document.getElementById('userAgentInput').style.display = "inline";
}

function logoutOption(){
    document.getElementById('causesInput').style.display = "inline";
    document.getElementById('timeInput').style.display = "inline";
    document.getElementById('typeOfTimeInput').style.display = "inline";
}

function registerOption(){
    document.getElementById('loginWithSmthInput').style.display = "inline";
    document.getElementById('timeInput').style.display = "inline";
    document.getElementById('typeOfTimeInput').style.display = "inline";
}

function searchOption(){
    document.getElementById("tagInput").style.display = "inline";
    document.getElementById('postDateInput').style.display = "inline";
    document.getElementById("pictureSizeInput").style.display = "inline";
    document.getElementById('timeInput').style.display = "inline";
    document.getElementById('typeOfTimeInput').style.display = "inline";
}

function crawlOption(){
    document.getElementById("tagInput").style.display = "inline";
    document.getElementById('postDateInput').style.display = "inline";
    document.getElementById("pictureSizeInput").style.display = "inline";
    document.getElementById('timeInput').style.display = "inline";
    document.getElementById('typeOfTimeInput').style.display = "inline";
}

function filtersOption(){
    document.getElementById('filtersTypeInput').style.display = "inline";
    document.getElementById('timeInput').style.display = "inline";
    document.getElementById('typeOfTimeInput').style.display = "inline";
}

function loginWith(){
    var optionNumber = document.getElementById("loginWithSmthInput").selectedIndex;
    var resultOptionValue = (document.getElementById("loginWithSmthInput").getElementsByTagName("option")[optionNumber].value);
    //document.getElementById('statisticResults').innerHTML = (document.getElementById("loginWithSmthInput").getElementsByTagName("option")[optionNumber].value);
    switch(resultOptionValue){
        case "NONE": 
            //do nothing!! just unshow the others options
            document.getElementById("ipInput").style.display = "none";
            break;
        case "IP":
            //the method with ip form
            withIP();            
            break;
        default: 
            document.getElementById('statisticResults').innerHTML = "Something is wrong! Try again!";
            break;
    }
}

function withNone(){
    
    document.getElementById("userAgentInput").style.display = "none";
}

function withIP(){
    document.getElementById("ipInput").style.display = "inline";
}

function withUserAgent(){
    document.getElementById("ipInput").style.display = "none";
    document.getElementById("userAgentInput").style.display = "inline";
}
