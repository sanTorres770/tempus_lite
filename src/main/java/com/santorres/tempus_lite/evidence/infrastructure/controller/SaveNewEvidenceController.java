package com.santorres.tempus_lite.evidence.infrastructure.controller;

import com.santorres.tempus_lite.evidence.use_case.SaveNewEvidenceUseCase;
import com.santorres.tempus_lite.user.domain.User;
import com.santorres.tempus_lite.user.use_case.GetUserByUserNameUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class SaveNewEvidenceController {
    private final SaveNewEvidenceUseCase saveNewEvidenceUseCase;
    private final GetUserByUserNameUseCase getUserByUserNameUseCase;

    public SaveNewEvidenceController(SaveNewEvidenceUseCase saveNewEvidenceUseCase, GetUserByUserNameUseCase getUserByUserNameUseCase) {
        this.saveNewEvidenceUseCase = saveNewEvidenceUseCase;
        this.getUserByUserNameUseCase = getUserByUserNameUseCase;
    }

    @GetMapping("/evidence/form/{fkTask}")
    public String form(@PathVariable String fkTask, Model model){

        model.addAttribute("fkTask",fkTask);


        return "/evidence/create_evidence_form";
    }

    @PostMapping("/evidence/save")
    public String save(@RequestParam Map<String,String> body, @RequestParam("fileName") MultipartFile dataImage,
                       RedirectAttributes flash, Authentication authentication) {


        String username = authentication.getName();

        User user = getUserByUserNameUseCase.getUserByUserName(username);

        String employeeName = user.getName() + " " + user.getLastName();

        body.put("createdBy",user.getId());
        body.put("employeeName",employeeName);


        if (!dataImage.isEmpty()) {

            String uniqueFileName = UUID.randomUUID() + "_" + trimImageName(Objects.requireNonNull(dataImage.getOriginalFilename()));
            String rootPath = "C:\\Users\\sanpe\\OneDrive\\Desktop\\uploads";

            body.put("dataImage", uniqueFileName);

            try {

                if (saveNewEvidenceUseCase.saveNewEvidence(body)) {
                    byte[] bytes = dataImage.getBytes();
                    Path allPath = Paths.get(rootPath + "//" + uniqueFileName);
                    Files.write(allPath, bytes);

                    flash.addFlashAttribute("success", "La evidencia se guardó correctamente.");
                    flash.addFlashAttribute("info", "Se subió correctamente la imágen de la evidencia '" + dataImage.getOriginalFilename() + "'");

                } else {
                    flash.addFlashAttribute("error", "La información no se guardó. Revise y vuelva a intentarlo.");
                    flash.addFlashAttribute("info", "La imágen no se subió correctamente. Reduzca el nombre del archivo y revise el formato para volver a intentarlo en la opción 'Editar toma de datos'.");
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());

            }
        }else {

            if (saveNewEvidenceUseCase.saveNewEvidence(body)) {

                flash.addFlashAttribute("success", "La evidencia se guardó correctamente.");

            } else {
                flash.addFlashAttribute("error", "La información no se guardó. Revise y vuelva a intentarlo.");
            }
        }

        return "redirect:/task/get/"+body.get("fkTask");

    }



    private String trimImageName (String originalImageName){
        String[] array = originalImageName.split("\\.");
        array[array.length-1] = "." + array[array.length-1];
        StringBuilder imageNameTrim = new StringBuilder();

        for (String words : array) {

            String[] array2 = words.split(" ");

            for (String word2: array2) {

                imageNameTrim.append(word2);
            }
        }

        return imageNameTrim.toString();
    }


}
