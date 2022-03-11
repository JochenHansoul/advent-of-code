"use strict";

const fs = require("fs");
const path = "../../resources/day10/example.txt";

const pairs = [
    ["(", ")"],
    ["[", "]"],
    ["{", "}"],
    ["<", ">"]
];

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const getCorruptedSymbol = line => {
    // ()()()<]
    // ()
    let difference = (line.length - line.replace(/\(/g, "").length)
        - (line.length - line.replace(/\)/g, "").length);
    console.log(difference);
    if (difference === -1) {
        return "]";
    }
    // []
    difference = (line.length - line.replace(/\[/g, "").length)
        - (line.length - line.replace(/\]/g, "").length);
    if (difference === -1) {
        return "]";
    }
    // {}
    difference = (line.length - line.replace(/\{/g, "").length)
        - (line.length - line.replace(/\}/g, "").length);
    if (difference === -1) {
        return "]";
    }
    // <>
    difference = (line.length - line.replace(/\</g, "").length)
        - (line.length - line.replace(/\>/g, "").length);
    if (difference === -1) {
        return "]";
    }
    return "";
};

const getCorruptedSymbols = lines => {
    const symbols = [];
    for (const line of lines) {
        symbols.push(getCorruptedSymbol(line));
    }
    return symbols;
};

// start code
const input = readFile(fs, path);
//console.log(input);
const s = getCorruptedSymbols(input);
console.log(s);
