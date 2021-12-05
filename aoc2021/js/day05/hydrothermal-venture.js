"use strict";

const fs = require("fs");
const path = "../../resources/day05/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const parseCoordinate = stringCoordinate => {
    const array = stringCoordinate.split(",");
    return {x: parseInt(array[0]), y: parseInt(array[1])};
};

// start code
const lines = readFile(fs, path);
const ventCoordinates = lines.map((x) => {
    const arrayCoordinates = x.split(/ -> /g);
    return [parseCoordinate(arrayCoordinates[0]), parseCoordinate(arrayCoordinates[1])];
    });
console.log(ventCoordinates);
