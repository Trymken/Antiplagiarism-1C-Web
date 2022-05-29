document.addEventListener("DOMContentLoaded", function(event) {

    let shingleLength = localStorage.getItem("shingleLength");
    let moduleVal = localStorage.getItem("moduleVal");
    let isModule = localStorage.getItem("isModule");
    let ngramLength = localStorage.getItem("ngramLength");
    let windowLength = localStorage.getItem("windowLength");
    let isShingles = localStorage.getItem("isShingles");
    let isLevenshtein = localStorage.getItem("isLevenshtein");
    let sort = localStorage.getItem("sort");
    let sortOrder = localStorage.getItem("sortOrder");
    let checkXml = localStorage.getItem("checkXml");
    let showZeroScore = localStorage.getItem("showZeroScore")

    if (shingleLength === null){
        document.getElementById("shingleLength").value = 10;
    } else {
        document.getElementById("shingleLength").value = shingleLength;
    }

    if (moduleVal === null){
        document.getElementById("moduleVal").value = 4;
    } else {
        document.getElementById("moduleVal").value = moduleVal;
    }

    if (isModule !== null){
        isModule = (isModule === 'true');
        document.getElementById("module").checked = isModule;
    }

    if (checkXml !== null){
        checkXml = (checkXml === 'true');
        document.getElementById("checkXml").checked = checkXml;
    }

    if (showZeroScore !== null){
        showZeroScore = (showZeroScore === 'true');
        document.getElementById("showZeroScore").checked = showZeroScore;
    }

    if (ngramLength === null){
        document.getElementById("ngramLength").value = 17;
    } else {
        document.getElementById("ngramLength").value = ngramLength;
    }

    if (windowLength === null){
        document.getElementById("windowLength").value = 5;
    } else {
        document.getElementById("windowLength").value = windowLength;
    }

    if (isShingles !== null){
        isShingles = (isShingles === 'true');
        document.getElementById("shingles").checked = isShingles;
    }

    if (isLevenshtein !== null){
        isLevenshtein = (isLevenshtein === 'true');
        document.getElementById("levenshtein").checked = isLevenshtein;
    }

    if (sort !== null){
        document.getElementById(sort).checked = true;
    }

    if (sortOrder !== null){
        document.getElementById(sortOrder).checked = true;
    }

    document.getElementById("saveSettings").onclick = function (){
        let shingleLength = document.getElementById("shingleLength").value;
        let moduleVal = document.getElementById("moduleVal").value;
        let isModule = document.getElementById("module").checked;
        let ngramLength = document.getElementById("ngramLength").value;
        let windowLength = document.getElementById("windowLength").value;
        let isShingles = document.getElementById("shingles").checked;
        let isLevenshtein = document.getElementById("levenshtein").checked;
        let sort = document.getElementsByName("sorting");
        let sortOrder = document.getElementsByName("sortOrder");
        let checkXml = document.getElementById("checkXml").checked;
        let showZeroScore = document.getElementById("showZeroScore").checked;

        localStorage.setItem("shingleLength", shingleLength);
        localStorage.setItem("moduleVal", moduleVal);
        localStorage.setItem("isModule", isModule);
        localStorage.setItem("ngramLength", ngramLength);
        localStorage.setItem("windowLength", windowLength);
        localStorage.setItem("isShingles", isShingles);
        localStorage.setItem("isLevenshtein", isLevenshtein);
        localStorage.setItem("checkXml", checkXml);
        localStorage.setItem("showZeroScore", showZeroScore);

        let idSort;
        for (let i = 0; i < sort.length; i++) {
            if(sort[i].checked){
                idSort = sort[i].id;
                break;
            }
        }

        localStorage.setItem("sort", idSort);

        let idOrderSort;
        for (let i = 0; i < sortOrder.length; i++) {
            if(sortOrder[i].checked){
                idOrderSort = sortOrder[i].id;
                break;
            }
        }

        localStorage.setItem("sortOrder", idOrderSort);
    };
});
