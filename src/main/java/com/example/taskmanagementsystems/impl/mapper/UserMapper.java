package com.example.taskmanagementsystems.impl.mapper;

import com.example.taskmanagementsystems.api.dto.request.RegistrationUserRequestDto;
import com.example.taskmanagementsystems.api.dto.response.DeleteUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.RegistrationUserResponseDto;
import com.example.taskmanagementsystems.api.dto.response.UserProfileResponseDto;
import com.example.taskmanagementsystems.db.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserProfileMapper {

  UserEntity mapToUserEntity(RegistrationUserRequestDto registrationUserRequestDto);

  RegistrationUserResponseDto mapToUserResponseDto(UserEntity userEntity);

  UserProfileResponseDto mapToUserProfileResponseDto(UserEntity userEntity);

  List<UserProfileResponseDto> toUserResponseList(List<UserEntity> users);

  DeleteUserResponseDto mapToDeleteUserResponseDto(UserEntity userEntity);

}
