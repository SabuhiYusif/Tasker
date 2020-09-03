package com.tasker.server.boards.rest

import com.tasker.server.boards.Board
import com.tasker.server.boards.rest.services.BoardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date
import java.util.UUID
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/boards")
class BoardCreationController(private val boardService: BoardService) {

    @PostMapping
    fun createBoard(@Valid @RequestBody request: BoardCreationRequest): ResponseEntity<BoardCreatedResponse> {

        val board = Board().apply {
            id = UUID.randomUUID()
            createdAt = Date()
            title = request.title
            description = request.description
        }
        boardService.create(board)

        return ResponseEntity(BoardCreatedResponse(board.id.toString()), HttpStatus.CREATED)
    }

    class BoardCreatedResponse(val id: String)
}