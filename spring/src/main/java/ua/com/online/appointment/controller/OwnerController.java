package ua.com.online.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.online.appointment.response.WorkerDTO;
import ua.com.online.appointment.request.WorkerScheduleRequest;
import ua.com.online.appointment.response.OwnerInfoResponse;
import ua.com.online.appointment.service.OwnerService;

import javax.servlet.ServletRequest;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/owner/{userId}")
    public ResponseEntity<OwnerInfoResponse> getOwner(@PathVariable Integer userId,ServletRequest servletRequest)
    {
        OwnerInfoResponse response = ownerService.getOwnerInfo(userId,servletRequest);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/owner/{userId}/workers")
    public ResponseEntity<List<WorkerDTO>> getWorkers(@PathVariable Integer userId, ServletRequest servletRequest){
        List<WorkerDTO> response = ownerService.getWorkers(userId,servletRequest);
        if(response != null){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/worker{workerId}")
    public HttpStatus deleteWorker(@PathVariable Integer workerId){
        return ownerService.deleteWorker(workerId);
    }
    @PutMapping("/owner/{userId}/description")
    public HttpStatus updateDescription(@PathVariable Integer userId,@RequestBody String description, ServletRequest servletRequest){
        return ownerService.updateCompanyDescription(userId,servletRequest,description);
    }
    @PutMapping("/owner/worker/{workerId}/schedule")
    public HttpStatus updateWorkerSchedule(@PathVariable Integer workerId, ServletRequest servletRequest, @RequestBody WorkerScheduleRequest workerScheduleRequest){
        return ownerService.updateWorkerSchedule(workerId,servletRequest,workerScheduleRequest);
    }
}
