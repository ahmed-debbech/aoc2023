const fs = require('fs');

function export_numbers(arr){

    //let map = {one : '1', two : '2', three : '3', four : '4', five : '5',
    //six : '6', seven : '7', eight : '8', nine : '9'}
    
    let ind = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine'];

    let numbers = []
    for(let i=0; i<=arr.length-1; i++){
        let num = ""
        let h=0
        console.log("**********")
        arr[i].split('').forEach((e)=>{
            if((e >= '0') && (e <= '9')){
                num += e
            }else{
                //otfsen
                if(arr[i][h] == 'o' || arr[i][h] == 't'
                || arr[i][h] == 'f' || arr[i][h] == 's'
                || arr[i][h] == 'e' || arr[i][h] =='n'){
                    for(let f = 0; f<=8; f++){
                        let xx = arr[i].substring(h, h + ind[f].length)
                        if(xx == ind[f]){
                            console.log(xx)
                            //console.log(xx)
                            num += (f+1).toString()
                        }
                    }
                }
            }
            h++
        })
        let k
        console.log(num)
        k = (num[0] + num[num.length-1])
        //console.log(k)
        numbers.push(Number(k))
    }
    return numbers;
}



let arr = []
const data = fs.readFileSync('input', 'utf8');

data.split('\n').forEach((e)=>{
    arr.push(e)
})


let numbers = []

numbers = export_numbers(arr);

let sum = 0
for(let i=0; i<=numbers.length-1; i++){
    sum += numbers[i]
}
console.log(sum)