"use strict";

const fs = require("fs")
const path = "../../resources/day01/input.txt"

fs.readFile(path, (err, data) => {
    if (err === null) {
        console.log(data.toString());
    } else {
        throw err;
    }
})
