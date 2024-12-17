package com.example.taskmanagementsystems.impl.mapper;

import com.example.taskmanagementsystems.api.dto.request.RegistrationTaskRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationTaskResponseDto;
import com.example.taskmanagementsystems.api.dto.response.TaskResponseDto;
import com.example.taskmanagementsystems.db.entity.TaskEntity;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

  TaskEntity mapToTaskEntity(RegistrationTaskRequestDto registrationTaskRequestDto);

  RegistrationTaskResponseDto mapToRegistrationTaskResponseDto(TaskEntity taskEntity);

  @Mapping(target = "authorId", source = "author")
  @Mapping(target = "executorId", source = "executor")
  TaskResponseDto mapToTaskDto(TaskEntity taskEntity);

  List<TaskResponseDto> toTaskResponseList(List<TaskEntity> tasks);

  DeleteTaskResponseDto mapToDeleteTaskResponseDto(TaskEntity taskEntity);

  default UUID map(UserEntity userEntity) {
    return userEntity != null ? userEntity.getUserId() : null;
  }

}
