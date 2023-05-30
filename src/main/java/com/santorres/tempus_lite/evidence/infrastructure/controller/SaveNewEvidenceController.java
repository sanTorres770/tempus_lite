package com.santorres.tempus_lite.evidence.infrastructure.controller;

import com.santorres.tempus_lite.evidence.use_case.SaveNewEvidenceUseCase;
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

    public SaveNewEvidenceController(SaveNewEvidenceUseCase saveNewEvidenceUseCase) {
        this.saveNewEvidenceUseCase = saveNewEvidenceUseCase;
    }

    @GetMapping("/evidence/form/{fkTask}")
    public String form(@PathVariable String fkTask, Model model){

        model.addAttribute("fkTask",fkTask);


        return "/evidence/create_evidence_form";
    }

    @PostMapping("/evidence/save")
    public String save(@RequestParam Map<String,String> body, @RequestParam("fileName") MultipartFile dataImage, RedirectAttributes flash) {


        if (!dataImage.isEmpty()) {

            String uniqueFileName = UUID.randomUUID() + "_" + trimImageName(Objects.requireNonNull(dataImage.getOriginalFilename()));
            String rootPath = "/home/byslnx/uploads/";

            body.put("dataImage",uniqueFileName);

            try {

                if (saveNewEvidenceUseCase.saveNewEvidence(body)) {
                    byte[] bytes = dataImage.getBytes();
                    Path allPath = Paths.get(rootPath + "//" + uniqueFileName);
                    Files.write(allPath, bytes);

                    flash.addAttribute("success", "La evidencia se guardó correctamente.");
                    flash.addFlashAttribute("success", "Se subió correctamente la imágen de la evidencia '" + dataImage.getOriginalFilename() + "'");

                } else {
                    flash.addAttribute("error", "La información no se guardó. Revise y vuelva a intentarlo.");
                    flash.addFlashAttribute("error", "La imágen no se subió correctamente. Reduzca el nombre del archivo y revise el formato para volver a intentarlo en la opción 'Editar toma de datos'.");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
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
