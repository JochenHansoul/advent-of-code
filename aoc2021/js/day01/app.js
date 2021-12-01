"use strict";

const fs = require("fs")
const path = "../../resources/day01/input.txt"

fs.readFile(path, (err, data) => {
    if (err === null) {
        let array = data.toString().split("\n");
        let counter = 0;
        let previousNumber = parseInt(array.shift());
        for (let s of array) {
            let n = parseInt(s);
            if (previousNumber < n) {
                counter++;
            }
            previousNumber = n;
        }
        console.log(counter);
    } else {
        throw err;
    }
})
