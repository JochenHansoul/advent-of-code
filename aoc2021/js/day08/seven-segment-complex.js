"use strict";

const fs = require("fs");
const path = "../../resources/day08/example.txt";

const searchedNumbers = [2, 4, 3, 7];

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};


/*
input:
ten unique signal patterns, a | delimiter, and finally the four digit output value

each use a unique number of segments:
1   2
4   4
7   3
8   7

question:
In the output values, how many times do digits 1, 4, 7, or 8 appear?
*/

// start code
const input = readFile(fs, path)
    .map((x) => {
        const array = x.split(" | ");
        return {
            pattern: array[0].split(/ /g),
            output: array[1].split(/ /g)
        };
    });

console.log("digit  ", [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]);
console.log("segment", [6, 2, 5, 5, 4, 5, 6, 3, 7, 6] );
for (let pattern of input[0].pattern) {
    let s = "";
    const length = pattern.length;
    if (searchedNumbers.includes(length)) {
        s = (2 === length) ? 1
            : (3 === length) ? 7
            : (4 === length) ? 4
            : 8;
    }
    console.log(pattern.length, pattern, s);
}

/*
alles eerst alfabetisch sorteren
eerst de 9 identificeren (bevat 4 met exact het verschil tussen 1 en 7)
daarna onderscheid maken tussen 0 en 6 doordat 0 ook dezelfde segmenten heeft als 1"
3 identificeren omdat deze dezelfde twee segmenten bevat als 1
5 identificeren omdat deze dezelfde twee segmenten vevat die niek zijn aan 4 (de twee van 1 verwijderen)
gevonden: 0, 1, 3, 4, 6, 7, 8, 9
nog zoeken: 2, 5


// 349 (correct, first part)
