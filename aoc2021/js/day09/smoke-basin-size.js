"use strict";

const fs = require("fs");
const path = "../../resources/day09/example.txt";

const readFile = (fs, path) => {
    return fs.readFileSync(path, "utf8")
        .split("\n")
        .slice(0, -1);
};

// returns an array of all the positions of low points, ex: [[0, 1], [2, 4]]
const getLowPoints = points => {
    const lowPoints = [];
    for (let i = 0; i < points.length; i++) {
        for (let j = 0; j < points[0].length; j++) {
            const value = points[i][j].v;
            // clockwise starting from upper position
            if ((i === 0 || value < points[i - 1][j].v)
                && (j === (points[i].length - 1) || value < points[i][j + 1].v)
                && (i === (points.length - 1) || value < points[i + 1][j].v)
                && (j === 0 || value < points[i][j - 1].v)) {
                    lowPoints.push([i, j]);
            }
        }
    }
    return lowPoints;
};

/*
* points    matrix of points
* lowpoint  coordinates of one low point [0, 1]
* return    array of all the lowpoints (objects) that are one value higher at the direct
*           cardinal directions of the lowpoint
*/
const getCardinalPointCoordinates = (points, lowPoint) => {
    const i = lowPoint[0];
    const j = lowPoint[1];
    const value = points[i][j].v + 1;
    const cardinalPointCoordinates = [];
    if (value < 9) {
        if (i !== 0 && value === (points[i - 1][j].v)) {
            cardinalPointCoordinates.push([i - 1, j]);
        }
        if (j !== (points[i].length - 1) && value === points[i][j + 1].v) {
            cardinalPointCoordinates.push([i, j + 1]);
        }
        if (i !== (points.length - 1) && value === points[i + 1][j].v) {
            cardinalPointCoordinates.push([i + 1, j]);
        }
        if (j !== 0 && value === points[i][j - 1].v) {
            cardinalPointCoordinates.push([i, j - 1]);
        }
    }
    //console.log(cardinalPointCoordinates);
    return cardinalPointCoordinates;
};

/*
* points    matrix of points
* lowpoint  coordinates of one low point [0, 1]
* return    size of the basin (number)
*/
const getBasinSize = (points, lowPoint) => {
    const set = new Set(); // these are objects
    set.add(points[lowPoint[0], lowPoint[1]]);
    let lowPointCoordinates = [lowPoint]; // these are coordinates [0, 1], not objects
    //console.log(lowPointCoordinates);
    while (lowPointCoordinates.length > 0) {
        const nextLowPointCoordinates = [];
        for (const lowPointCoordinate of lowPointCoordinates) {
            // addint the points to the set and the coordinates to the nextLowPointCoordinates array
            for (const coordinate of getCardinalPointCoordinates(points, lowPointCoordinate)) {
                nextLowPointCoordinates.push(coordinate);
                set.add(points[coordinate[0]][coordinate[1]]);
                //console.log(points[coordinate[0]][coordinate[1]]);
            }
        }
        //console.log(set.size);
        lowPointCoordinates = nextLowPointCoordinates;
    }
    return set.size;
};



// start code
const input = readFile(fs, path).map((x) => {
    return x.split("").map((y) => {
        return {v: parseInt(y)}
    })
});
let sizes = [];
for (let i = 0; i < getLowPoints(input).length; i++) {
    sizes.push(getBasinSize(input, getLowPoints(input)[i]));
}
console.log("size:", sizes);
