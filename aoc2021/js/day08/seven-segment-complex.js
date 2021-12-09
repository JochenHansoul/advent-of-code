"use strict";

const fs = require("fs");
const path = "../../resources/day08/input.txt";

const searchedNumbers = [2, 4, 3, 7];

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const removeFromArray = (array, toBeRemoved) => {
    const output = array.slice();
    for (const remove of toBeRemoved) {
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
    const map = new Map();
    const sortedPatterns = patterns.map((x) => x.split("").sort());
    let one;
    let four;
    let seven;
    let eight;
    for (let pattern of sortedPatterns) {
        const length = pattern.length;
        if (searchedNumbers.includes(length)) {
            if (length === 2) {
                map.set(pattern.join(""), 1);
                one = pattern;
            } else if (length === 4) {
                map.set(pattern.join(""), 4);
                four = pattern;
            } else if (length === 3) {
                map.set(pattern.join(""), 7);
                seven = pattern;
            } else {
                map.set(pattern.join(""), 8);
                eight = pattern;
            }
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(one), 1);
    sortedPatterns.splice(sortedPatterns.indexOf(four), 1);
    sortedPatterns.splice(sortedPatterns.indexOf(seven), 1);
    sortedPatterns.splice(sortedPatterns.indexOf(eight), 1);
    seven = removeFromArray(seven, one);
    let nine;
    for (let pattern of sortedPatterns) {
        if (pattern.length === 6
                && pattern.includes(seven[0])
                && pattern.includes(four[0])
                && pattern.includes(four[1])
                && pattern.includes(four[2])
                && pattern.includes(four[3])) {
            map.set(pattern.join(""), 9);
            nine = pattern;
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(nine), 1);
    let zero;
    let six;
    for (let pattern of sortedPatterns) {
        if (pattern.length === 6) {
            if (pattern.includes(one[0]) && pattern.includes(one[1])) {
                map.set(pattern.join(""), 0);
                zero = pattern;
            } else {
                map.set(pattern.join(""), 6);
                six = pattern;
            }
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(zero), 1); // removing 0
    sortedPatterns.splice(sortedPatterns.indexOf(six), 1); // removing 6
    let tree;
    for (let pattern of sortedPatterns) {
        if (pattern.includes(one[0]) && pattern.includes(one[1])) {
            map.set(pattern.join(""), 3);
            tree = pattern;
        }
    }
    sortedPatterns.splice(sortedPatterns.indexOf(tree), 1); // removing 3
    four = removeFromArray(four, one);
    // it's unnecessary to remove the last two numbers
    for (let pattern of sortedPatterns) {
        if (pattern.includes(four[0]) && pattern.includes(four[1])) {
            map.set(pattern.join(""), 5);
        } else {
            map.set(pattern.join(""), 2);
        }
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
