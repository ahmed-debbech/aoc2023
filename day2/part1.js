const fs = require('fs');

let games = []
const data = fs.readFileSync('input', 'utf8');

data.split('\n').forEach((e)=>{
    games.push(e)
})
console.log(games)

function seeSet(sets){
    for(let i=0; i<=sets.length-1; i++){
        console.log("**********")
        console.log(sets[i].gameid)
        for(let j=0; j<=sets[i].set.length-1; j++){
            console.log("set " + j)
            for(let h=0; h<=sets[i].set[j].length-1; h++){
                console.log(sets[i].set[j][h])
            }
        }
    }
}
function parser(){

    let sets = []
    for(let i=0; i<=games.length-1; i++){
        let words = games[i].split(" ")
        let y = {
            gameid: words[1].substring(0,1),
            set : []
        }
        let d = []
        let k = {
            num:'',
            color:''
        }
        // /console.log(words)
        for(let j=2; j<=words.length-1; j=j+2){
            k.num = words[j]
            if((words.length-1 != (j+1)))
            k.color = words[j+1].substring(0,words[j+1].length-1)
            d.push({ num : k.num, color: k.color})

            if((words[j+1][words[j+1].length-1] == ';') || (words.length-1 == (j+1))){
                y.set.push(new Array(d))
                d = []
            }

        }
        sets.push(y)
    }
    return sets;
}

function check(sets){
    let res = 0
    for(let i=0; i<=sets.length-1; i++){
        for(let  j=0; j<=sets[i].set.length-1; j++){
            for(let k =0; k<=sets[i].set[j].length-1;k++){
                console.log((sets[i].set[j][0]))
                if(sets[i].set[j][k].color = 'green'){
                    if(Number(sets[i].set[j][k].num) <= 13) res += Number(sets[i].gameid)
                }
                if(sets[i].set[j][k].color = 'blue'){
                    if(Number(sets[i].set[j][k].num) <= 14) res += Number(sets[i].gameid)
                }
                if(sets[i].set[j][k].color = 'red'){
                    if(Number(sets[i].set[j][k].num) <= 12) res += Number(sets[i].gameid)
                }
            }
        }
    }
    return res
}

let sets = parser()
//console.log(sets)
seeSet(sets)
