async function get(fno){// 비대칭 통신(비동기)
    const result = await axios.get(`/replies/list/${fno}`)
    console.log(result)
    return result.data;
}

async function getList({fno, page, size, goLast}){// 비대칭 통신(비동기)
    const result = await axios.get(`/replies/list/${fno}`,
        {params : {page,size}})
    // console.log(result)
    return result.data;
}

async function getList({fno, page, size, goLast}){// 비대칭 통신(비동기)
    const result = await axios.get(`/replies/list/${fno}`,
        {params : {page,size}})
    // console.log(result)
    if(goLast){
        const total = result.data.total;
        const lastpage = parseInt(Math.ceil(total/size))
        return getList({fno:fno, page:lastpage, size:size})
    }
    return result.data;
}

// 댓글 등록
async function addReply(replyObj){
    const responce = await axios.post(`/replies/`, replyObj)
    return responce.data
}