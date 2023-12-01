const fs = require('fs');

let arr = []
const data = fs.readFileSync('input', 'utf8');

data.split('\n').forEach((e)=>{
    arr.push(e)
})
console.log(arr)


let numbers = []
for(let i=0; i<=arr.length-1; i++){
    let num = ""
    arr[i].split('').forEach((e)=>{
        if((e >= '0') && (e <= '9')){
            num += e
        }
    })
    let k
    k = (num[0] + num[num.length-1])
    console.log(k)
    numbers.push(Number(k))
}

console.log(numbers)
let sum = 0
for(let i=0; i<=numbers.length-1; i++){
    sum += numbers[i]
}
console.log(sum)