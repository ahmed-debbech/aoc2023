var fs = require('fs');

let data = fs.readFileSync('input', 'utf-8');

let arr = data.split(',')

console.log(arr)

function hash(string){
    let curr = 0
    for(let i=0; i<=string.length-1; i++){
        curr += string.charCodeAt(i)
        curr = curr * 17
        curr = curr % 256
    }
    return curr
}

let result = 0
for(let i=0; i<=arr.length-1; i++){
    result += hash(arr[i])
}

console.log("RESULT: " + result)