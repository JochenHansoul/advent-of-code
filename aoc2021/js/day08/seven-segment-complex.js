"use strict";

const fs = require("fs");
const path = "../../resources/day08/input.txt";

const searchedNumbers = [2, 4, 3, 7];

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const removeNumbers = (numbers, removeNumbers) => {
    const output = numbers.slice();
    for (const remove of removeNumbers) {
        const index = output.indexOf(remove);
        if (index > -1) {
            output.splice(index, 1);
        }
    }
    return output;
};


/*
++alles eerst alfabetisch sorteren
++de vier unieke nummers identificeren
++eerst de 9 identificeren (bevat 4 met exact het verschil tussen 1 en 7)
++daarna onderscheid maken tussen 0 en 6 doordat 0 ook dezelfde segmenten heeft als 1"
++3 identificeren omdat deze dezelfde twee segmenten bevat als 1
++5 identificeren omdat deze dezelfde twee segmenten bevat die uniek zijn aan 4 (de twee van 1 verwijderen)
++2 blijft over
*/
const getMap = patterns => {
    const sortedPatterns = patterns.map((x) => x.split("").sort().join(""));
    const numbers = [undefined, undefined, undefined, undefined, undefined, undefined, undefined, undefined, undefined, undefined]; 
    for (const pattern of sortedPatterns) {
        const length = pattern.length;
        if (searchedNumbers.includes(length)) {
            if (length === 2) {
                numbers[1] = pattern;
            } else if (length === 4) {
                numbers[4] = pattern;
            } else if (length === 3) {
                numbers[7] = pattern;
            } else {
                numbers[8] = pattern;
            }
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[1]), 1);
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[4]), 1);
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[7]), 1);
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[8]), 1);
    const sevenDifference = numbers[7].replace(numbers[1].charAt(0), "").replace(numbers[1].charAt(1), "");// removeNumbers(seven.split(""), one.split(""));
    for (const pattern of sortedPatterns) {
        if (pattern.length === 6
                && pattern.includes(sevenDifference[0])
                && pattern.includes(numbers[4][0])
                && pattern.includes(numbers[4][1])
                && pattern.includes(numbers[4][2])
                && pattern.includes(numbers[4][3])) {
            numbers[9] = pattern;
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[9]), 1);
    for (const pattern of sortedPatterns) {
        if (pattern.length === 6) {
            if (pattern.includes(numbers[1][0]) && pattern.includes(numbers[1][1])) {
                numbers[0] = pattern;
            } else {
                numbers[6] = pattern;
            }
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[0]), 1); // removing 0
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[6]), 1); // removing 6
    for (const pattern of sortedPatterns) {
        if (pattern.includes(numbers[1][0]) && pattern.includes(numbers[1][1])) {
            numbers[3] = pattern;
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(numbers[3]), 1); // removing 3
    const fourDifference = numbers[4].replace(numbers[1][0], "").replace(numbers[1][1], "");// removeNumbers(four.split(""), one.split(""));
    const lastPattern = sortedPatterns.pop();
    if (lastPattern.includes(fourDifference[0]) && lastPattern.includes(fourDifference[1])) {
        numbers[5] = lastPattern;
        numbers[2] = sortedPatterns.pop();
    } else {
        numbers[2] = lastPattern;
        numbers[5] = sortedPatterns.pop();
    }
    const map = new Map();
    for (let i = 0; i < numbers.length; i++) {
        map.set(numbers[i], i);
    }
    return map;
}

const getOutputNumber = o => {
    const map = getMap(o.pattern);
    const sortedNumbers = o.output.map((x) => x.split("").sort());
    let sum = "";
    for (const sortedNumber of sortedNumbers) {
        sum += map.get(sortedNumber.join(""));
    }
    return parseInt(sum, 10);
}


// start code
const input = readFile(fs, path)
    .map((x) => {
        const array = x.split(" | ");
        return {
            pattern: array[0].split(/ /g),
            output: array[1].split(/ /g)
        };
    });

let sum = 0;
for (const o of input) {
    sum += getOutputNumber(o);
}
console.log(sum);

// 1069670 (too low)
// 1070957 (correct)

/*
I had some trouble with the removeFronArray function (why can't they just remove the first element of the array or somethings?)
I accidentaly put a reference to the original array into a variable and removed the elements of the original array, then I
forgot to change the "array".index() name and had to check if the element does not excist
I also had a lot of trouble with removing the original numbers from the sortedArray AFTER the for loop otherwise it created
incorrect values
*/
