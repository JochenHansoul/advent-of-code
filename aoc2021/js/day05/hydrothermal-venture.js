"use strict";

const fs = require("fs");
const path = "../../resources/day05/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const isHorizontalOrVertical = (coordinate1, coordinate2) => {
    return coordinate1.x === coordinate2.x || coordinate1.y === coordinate2.y;
};

const parseCoordinate = stringCoordinate => {
    const array = stringCoordinate.split(",");
    return {x: parseInt(array[0]), y: parseInt(array[1])};
};

const markDiagram = (diagram, coordinate1, coordinate2) => {
    let lowest;
    let highest;
    if (coordinate1.x === coordinate2.x) {
        // mark y axis
        if (coordinate1.y < coordinate2.y) {
            highest = coordinate2.y;
            lowest = coordinate1.y;
        } else {
            highest = coordinate1.y;
            lowest = coordinate2.y;
        }
        for (let i = lowest; i <= highest; i++) {
            diagram[coordinate1.x][i]++;
        }
    } else {
        // mark x axis
        if (coordinate1.x < coordinate2.x) {
            highest = coordinate2.x;
            lowest = coordinate1.x;
        } else {
            highest = coordinate1.x;
            lowest = coordinate2.x;
        }
        for (let i = lowest; i <= highest; i++) {
            diagram[i][coordinate1.y]++;
        }
    }
};


// start code
const lines = readFile(fs, path);
const ventCoordinates = lines.map((x) => {
    const arrayCoordinates = x.split(/ -> /g);
    return [parseCoordinate(arrayCoordinates[0]), parseCoordinate(arrayCoordinates[1])];
    });

const diagram = [];
for (let i = 0; i < 10; i++) {
    diagram.push(new Array(10).fill(0));
};

for (let i = 0; i < ventCoordinates.length; i++) {
    const thermalLine = ventCoordinates[i];
    if (isHorizontalOrVertical(thermalLine[0], thermalLine[1])) {
        markDiagram(diagram, thermalLine[0], thermalLine[1]);
    }
}
console.log(diagram);
