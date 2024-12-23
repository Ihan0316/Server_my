// 게시글 조회
async function get(blogno){
    const result = await axios.get(`/replies/list/${blogno}`)
    console.log(result)
    return result.data;
}

// 개시글 정보 조회
// async function getList({blogno, page, size, goLast}){
//     const result = await axios.get(`/replies/list/${blogno}`,
//         {params : {page,size}})
//     return result.data;
// }

// 마지막 댓글 위치로 이동하기
async function getList({blogno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${blogno}`,
        {params : {page,size}})
    if(goLast){
        const total = result.data.total;
        const lastpage = parseInt(Math.ceil(total/size))
        return getList({blogno:blogno, page:lastpage, size:size})
    }
    return result.data;
}

// 댓글 등록
async function addReply(replyObj){
    const response = await axios.post(`/replies/`, replyObj)
    return response.data
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
