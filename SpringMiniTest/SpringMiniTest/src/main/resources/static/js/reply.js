    // 가져오기
async function get(fno){// 비대칭 통신(비동기)
    const result = await axios.get(`/replies/list/${fno}`)
    console.log(result)
    return result.data;
}

// async function getList({fno, page, size}){// 비대칭 통신(비동기)
//     const result = await axios.get(`/replies/list/${fno}`,
//         {params : {page,size}})
//     return result.data;
// }

async function getList({fno, page, size, goLast}){// 비대칭 통신(비동기)
    const result = await axios.get(`/replies/list/${fno}`,
        {params : {page,size}})
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

// 댓글 조회
async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

// 댓글 수정, 수정댓글 내용 포함 replyObj
async function updateReply(replyObj){
    const response = await axios.put(`/replies/${replyObj.rno}`,replyObj)
    return response.data
}

// 댓글 삭제, 댓글 번호만 필요함 rno
async function deleteReply(rno){
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}