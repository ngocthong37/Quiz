package com.example.quizzz

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION:String = "total_question"
    const val CORRECT_ANSWER:String = "correct_answer"
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val que1 = Question(
            1, "Đây là tỉ phú nào?",
            R.drawable.ic_elon_mush,"MarkZukerberg",
            "Jack Ma", "Phạm Nhật Vượng",
            "Elon Musk",
            4

        )
        questionList.add(que1)
        val que2 = Question (
            2, "Đây là hình ảnh gì",
            R.drawable.ic_thanh_pho_vinh,
            "Thành phố Vinh",
            "Thành phố Hà Tĩnh",
            "Hình ảnh những con người của đất nước",
            "Cả 3 đáp án trên đều đúng",
            4
        )
        questionList.add(que2)
        val que3 = Question (
            3, "Đây là ai",
            R.drawable.ic_ngiu,
            "Của nợ đời Thông",
            "Em sai rồi tất cả là lỗi của anh",
            "Con lợn 44 kg",
            "Tất cả đáp án trên đều đúng",
            4
        )
        questionList.add(que3)
        return questionList
    }
}