//package com.librarymanagement.maids.mapper;
//
//import com.librarymanagement.maids.domain.BorrowingRecord;
//import com.librarymanagement.maids.dto.BorrowingRecordDTO;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//
//import com.librarymanagement.maids.domain.RequestModel.BorrowingRecordRequest;
//
//import org.modelmapper.PropertyMap;
//
//
//@Component
//public class BorrowingRecordMapper {
//
//    private final ModelMapper modelMapper;
//
//    public BorrowingRecordMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//
//        // Custom mapping configuration for BorrowingRecordRequest to BorrowingRecord
//        modelMapper.addMappings(new PropertyMap<BorrowingRecordRequest, BorrowingRecord>() {
//            @Override
//            protected void configure() {
//                skip(destination.getId()); // Skip setting the ID field to avoid conflicts
//            }
//        });
//
//        // Custom mapping configuration for BorrowingRecordDTO to BorrowingRecord
//        modelMapper.addMappings(new PropertyMap<BorrowingRecordDTO, BorrowingRecord>() {
//            @Override
//            protected void configure() {
//                skip(destination.getId()); // Skip setting the ID field to avoid conflicts
//                map().getBook().setId(source.getBook().getId());
//                map().getPatron().setId(source.getPatron().getId());
//            }
//        });
//    }
//
//    public BorrowingRecord convertToEntity(BorrowingRecordDTO borrowingRecordDTO) {
//        return modelMapper.map(borrowingRecordDTO, BorrowingRecord.class);
//    }
//
//    public BorrowingRecordDTO convertToDto(BorrowingRecord borrowingRecord) {
//        return modelMapper.map(borrowingRecord, BorrowingRecordDTO.class);
//    }
//}
