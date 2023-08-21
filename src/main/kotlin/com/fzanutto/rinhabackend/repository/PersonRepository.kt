package com.fzanutto.rinhabackend.repository

import com.fzanutto.rinhabackend.entity.PersonEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PersonRepository: CrudRepository<PersonEntity, UUID> {

}