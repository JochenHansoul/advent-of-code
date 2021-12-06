"use strict";

const fs = require("fs");
const path = "../../resources/day05/input.txt";
const size = 999;

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

const parseCoordinate = stringCoordinate => {
    const array = stringCoordinate.split(",");
    return {x: parseInt(array[0]), y: parseInt(array[1])};
};

const createDiagram = size => {
    const diagram = [];
    for (let i = 0; i < size; i++) {
        diagram.push(new Array(size).fill(0));
    };
    return diagram;
};

const goesDown = (coordinate1, coordinate2) => {
    return (coordinate1.x < coordinate2.x && coordinate1.y < coordinate2.y)
        || (coordinate2.x < coordinate1.x && coordinate2.y < coordinate1.y);
};

const markLine = (diagram, coordinate1, coordinate2) => {
    if (coordinate1.x === coordinate2.x) {
        // mark y axis
        const highest = Math.max(coordinate1.y, coordinate2.y);
        const lowest = Math.min(coordinate1.y, coordinate2.y);
        for (let i = lowest; i <= highest; i++) {
            diagram[i][coordinate1.x]++;
        }
    } else if (coordinate1.y === coordinate2.y) {
        // mark x axis
        const highest = Math.max(coordinate1.x, coordinate2.x);
        const lowest = Math.min(coordinate1.x, coordinate2.x);
        for (let i = lowest; i <= highest; i++) {
            diagram[coordinate1.y][i]++;
        }
    } else if (goesDown(coordinate1, coordinate2)) {
        // goes down
        const highestX = Math.max(coordinate1.x, coordinate2.x);
        const lowestX = Math.min(coordinate1.x, coordinate2.x);
        const lowestY = Math.min(coordinate1.y, coordinate2.y);
        for (let i = 0; i <= (highestX - lowestX); i++) {
            diagram[lowestY + i][lowestX + i]++;
        }
    } else {
        // goes up
        const lowest = (coordinate1.x < coordinate2.x) ? coordinate1 : coordinate2;
        const highest = (coordinate1.x < coordinate2.x) ? coordinate2 : coordinate1;
        const difference = Math.abs(coordinate1.x - coordinate2.x);
        for (let i = 0; i <= difference; i++) {
            diagram[lowest.y - i][lowest.x + i]++;
        }
    }
};

const markDiagram = (diagram, coordinates) => {
    for (const thermalLine of coordinates) {
        markLine(diagram, thermalLine[0], thermalLine[1]);
    }
};

const findCrossings = diagram => {
    let sum = 0;
    for (let row of diagram) {
        for (let n of row) {
            if (n > 1) {
                sum++;
            }
        }
    }
    return sum;
};


// start code
const lines = readFile(fs, path);
// getting coordinates
const ventCoordinates = lines.map((x) => {
    const arrayCoordinates = x.split(/ -> /g);
    return [parseCoordinate(arrayCoordinates[0]), parseCoordinate(arrayCoordinates[1])];
    });

const diagram = createDiagram(size);
markDiagram(diagram, ventCoordinates);
console.log(findCrossings(diagram));
// 19652 (too low)
// 19676 (right)
